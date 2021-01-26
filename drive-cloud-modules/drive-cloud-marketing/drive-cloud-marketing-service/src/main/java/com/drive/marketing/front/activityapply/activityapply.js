import request from '@/utils/request'


// 分页查询活动参加记录表列表
export function listPageActivityApply(query) {
  return request({
    url: '/marketing/activityApply/pageList',
    method: 'post',
    params: query
  })
}


// 分页查询活动参加记录表列表
export function listActivityApply(query) {
  return request({
    url: '/marketing/activityApply/findList',
    method: 'post',
    params: query
  })
}

// 查询活动参加记录表详细
export function getActivityApply(id) {
  return request({
    url: '/marketing/activityApply/' +id,
    method: 'get'
  })
}

// 新增活动参加记录表
export function addActivityApply(data) {
  return request({
    url: '/marketing/activityApply',
    method: 'post',
    data: data
  })
}

// 修改活动参加记录表
export function updateActivityApply(data) {
  return request({
    url: '/marketing/activityApply',
    method: 'put',
    data: data
  })
}

// 删除活动参加记录表
export function delActivityApply(id) {
  return request({
    url: '/marketing/activityApply/' +id},
    method: 'delete'
  })
}


// 状态启用/停用活动参加记录表
export function changeStatus(data) {
  return request({
    url: '/marketing/activityApply/changeStatus',
    method: 'post',
    data: data
  })
}

// 导出活动参加记录表
export function exportActivityApply(query) {
  return request({
    url: '/marketing/activityApply/exportXls',
    method: 'post',
    params: query,
    responseType: 'blob'
  })
}