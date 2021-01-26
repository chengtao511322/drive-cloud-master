import request from '@/utils/request'


// 分页查询推广渠道经理列表
export function listPageRecommendManager(query) {
  return request({
    url: '/admin/recommendManager/pageList',
    method: 'post',
    params: query
  })
}


// 分页查询推广渠道经理列表
export function listRecommendManager(query) {
  return request({
    url: '/admin/recommendManager/findList',
    method: 'post',
    params: query
  })
}

// 查询推广渠道经理详细
export function getRecommendManager(id) {
  return request({
    url: '/admin/recommendManager/' + id,
    method: 'get'
  })
}

// 新增推广渠道经理
export function addRecommendManager(data) {
  return request({
    url: '/admin/recommendManager',
    method: 'post',
    data: data
  })
}

// 修改推广渠道经理
export function updateRecommendManager(data) {
  return request({
    url: '/admin/recommendManager',
    method: 'put',
    data: data
  })
}

// 删除推广渠道经理
export function delRecommendManager(id) {
  return request({
    url: '/admin/recommendManager/' + id,
    method: 'delete'
  })
}


// 状态启用/停用推广渠道经理
export function changeStatus(data) {
  return request({
    url: '/admin/recommendManager/changeStatus',
    method: 'post',
    data: data
  })
}

// 导出推广渠道经理
export function exportRecommendManager(query) {
  return request({
    url: '/admin/recommendManager/exportXls',
    method: 'post',
    params: query,
    responseType: 'blob'
  })
}