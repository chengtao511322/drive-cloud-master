import request from '@/utils/request'

              
// 分页查询学员教练评价明细表列表
export function listPageEvaluateTagAppraise(query) {
  return request({
    url: '/admin/evaluateTagAppraise/pageList',
    method: 'get',
    params: query
  })
}


// 分页查询学员教练评价明细表列表
export function listEvaluateTagAppraise(query) {
  return request({
    url: '/admin/evaluateTagAppraise/findList',
    method: 'get',
    params: query
  })
}

// 查询学员教练评价明细表详细
export function getEvaluateTagAppraise(id) {
  return request({
    url: '/admin/evaluateTagAppraise/' + id,
    method: 'get'
  })
}

// 新增学员教练评价明细表
export function addEvaluateTagAppraise(data) {
  return request({
    url: '/admin/evaluateTagAppraise',
    method: 'post',
    data: data
  })
}

// 修改学员教练评价明细表
export function updateEvaluateTagAppraise(data) {
  return request({
    url: '/admin/evaluateTagAppraise',
    method: 'put',
    data: data
  })
}

// 删除学员教练评价明细表
export function delEvaluateTagAppraise(id) {
  return request({
    url: '/admin/evaluateTagAppraise/' + id,
    method: 'delete'
  })
}


// 状态启用/停用学员教练评价明细表
export function changeStatus(data) {
  return request({
    url: '/admin/evaluateTagAppraise/changeStatus',
    method: 'post',
    data: data
  })
}

// 导出学员教练评价明细表
export function exportEvaluateTagAppraise(query) {
  return request({
    url: '/admin/evaluateTagAppraise/exportXls',
    method: 'post',
    params: query,
    responseType: 'blob'
  })
}